package com.haoran.data.elastic;

import com.google.common.base.Stopwatch;
import com.haoran.common.Constants;
import com.haoran.common.NtConfig;
import com.haoran.common.Parser;
import com.haoran.common.u.U4Object;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hr.han
 * @date 2019/6/5 10:06
 */
class ElasticClientLoader {
    private ElasticClientLoader() {}

    static TransportClient load() {
        return Handler.INSTANCE;
    }

    private static final Logger logger = LoggerFactory.getLogger(ElasticClientLoader.class);
    private static final String KEY_ELASTIC_PREFIX = "elastic.server.";
    private static final String KEY_ADDRESSES = "addresses";

    private static final class Handler {
        static final TransportClient INSTANCE;
        static {
            Stopwatch stopwatch = Stopwatch.createStarted();

            TransportClient tc;
            try {
                String addressesStr = NtConfig.getProperty(KEY_ELASTIC_PREFIX + KEY_ADDRESSES);
                if (U4Object.isNull(addressesStr)) {
                    addressesStr = NtConfig.getProperty("elastic.properties", KEY_ADDRESSES);
                }

                String[] addresses = addressesStr.split(";");
                tc = new PreBuiltTransportClient(Settings.EMPTY);

                String host;
                Integer port;

                for (String address : addresses) {
                    if (U4Object.isNullOrEmpty(address)) {
                        continue;
                    }

                    String[] parts = address.split(":");
                    host = parts[0];
                    if (U4Object.isNullOrEmpty(host)) {
                        continue;
                    }

                    if (parts.length == Constants.ONE) {
                        port = 9300;
                    } else {
                        port = Parser.parse2Integer(parts[1]);
                    }

                    tc.addTransportAddress(
                            new InetSocketTransportAddress(
                                    InetAddress.getByName(host),
                                    port
                            )
                    );
                }
            } catch (UnknownHostException e) {
                tc = null;
                logger.error("unknown host", e);
                throw new IllegalStateException("es init failed");
            }

            INSTANCE = tc;
            Runtime.getRuntime().addShutdownHook(Executors.defaultThreadFactory().newThread(INSTANCE::close));
            logger.info("ElasticSearch client init success with {} ms...", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        }
    }

}
