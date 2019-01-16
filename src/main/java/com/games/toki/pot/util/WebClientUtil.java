package com.games.toki.pot.util;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

public class WebClientUtil {

    public static WebClient build(String baseURL, int connectionTimeoutInMillis, int readTimeoutInMillis)
    {

        TcpClient tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeoutInMillis)
                .doOnConnected(connection -> connection.addHandlerLast(new ReadTimeoutHandler(readTimeoutInMillis)));

        return WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient).compress(Boolean.TRUE)))
                .baseUrl(baseURL).build();
    }
}
