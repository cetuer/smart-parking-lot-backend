package com.cetuer.gateway.handler;

import com.cetuer.parking.common.enums.ResultCode;
import com.cetuer.parking.common.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author Cetuer
 * @date 2021/12/9 11:09
 */
@Slf4j
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());

        if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            return ServletUtils.webFluxResponseWriter(response, ResultCode.GATEWAY_ERROR, responseStatusException.getStatus().toString());
        } else {
            return ServletUtils.webFluxResponseWriter(response, ResultCode.GATEWAY_ERROR);
        }
    }
}