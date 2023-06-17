package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.vo.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 라이프사이클과 관련된 메서드
 * {@link Servlet#init(ServletConfig)} : 서블릿 컨테이너가 서블릿 생성후 초기화 작업을 수행하는 메서드
 * {@link Servlet#service(ServletRequest, ServletResponse)}
 * {@link Servlet#destroy()}
 *
 * 기타 메서드
 * {@link Servlet#getServletConfig()}
 * {@link Servlet#getServletInfo()}
 */
@WebServlet("/calculate")//url path 와 servlet 매칭을 위해 사용
public class CalculatorServlet implements Servlet {

    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        String result = String.valueOf(
                new Calculator().calculate(
                        new PositiveNumber(operand1),
                        operator,
                        new PositiveNumber(operand2))
        );
        response.getWriter()
                .write(result);
    }

    @Override
    public void destroy() {
        log.info("destroy");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}
