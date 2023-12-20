package com.vian.gateway.service.infrastructure.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class TokenHeaderRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenHeaderRoutePredicateFactory.Config> {

    public TokenHeaderRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            Set<Map.Entry<String, List<String>>> headers = exchange.getRequest().getHeaders().entrySet();
            for(Map.Entry me : headers){
                if(me.getKey().equals(config.getHeaderName())){
                    return true;
                }
            }
            return false;
        };
    }

    public static class Config {
        private String headerName;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }
    }

    @Override
    //[Token,1111-2222-3333-4444]
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerName","value");
    }
}
