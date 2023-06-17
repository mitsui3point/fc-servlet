package org.example;

import org.apache.catalina.LifecycleException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.example.test.TomcatTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorServletTest {
    @InjectMocks
    CalculatorServlet calculatorServlet;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    ServletConfig config;

    @BeforeEach
    void setUp() {
        //given
        calculatorServlet = Mockito.mock(CalculatorServlet.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        config = Mockito.mock(ServletConfig.class);
    }

    @DisplayName("CalculatorServlet 을 생성한다.")
    @Test
    void create() {
        CalculatorServlet calculatorServlet = new CalculatorServlet();
        //then
        assertThat(calculatorServlet).isInstanceOf(Servlet.class);
    }

    @DisplayName("서블릿 init 호출을 성공한다.")
    @Test
    void init() throws ServletException, IOException {
        //when
        calculatorServlet.init(config);
        //then
        Mockito.verify(calculatorServlet, Mockito.times(1)).init(config);
    }

    @DisplayName("서블릿 service 호출을 성공한다.")
    @Test
    void service() throws ServletException, IOException {
        //when
        calculatorServlet.service(request, response);
        //then
        Mockito.verify(calculatorServlet, Mockito.times(1)).service(request, response);
    }

    @DisplayName("/calculate 요청에 성공한다.")
    @Test
    void servletWithCalculateService() throws IOException, LifecycleException {
        new TomcatTest(() -> {
            int operand1 = 11;
            String operator = "*";
            int operand2 = 55;
            String uri = new StringBuilder("http://localhost:8090/calculate")
                    .append("?").append("operand1=").append(operand1)
                    .append("&").append("operator=").append(operator)
                    .append("&").append("operand2=").append(operand2)
                    .toString();
            HttpGet request = new HttpGet(uri);

            //when
            CloseableHttpResponse response = HttpClientBuilder.create()// HttpClient 생성
                    .build()
                    .execute(request);
            //then
            int responseCode = response
                    .getStatusLine()
                    .getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            //then
            assertThat(responseCode).isEqualTo(200);
            assertThat(responseBody).isEqualTo("605");
        }).run();
    }
}
