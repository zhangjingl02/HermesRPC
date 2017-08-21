package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.IOException;

/**
 * Created by ECFGGIO on 8/21/2017.
 */
public class LogUtils {

    private static Logger CONNECTION_LOG;

    public static void init() throws IOException {
        ConfigurationSource source = new ConfigurationSource(LogUtils.class.getResourceAsStream("/log4j.xml"));
        Configurator.initialize(null, source);
        CONNECTION_LOG = LogManager.getLogger("connection");
    }


    public static void connectionInfo(String msg){
        CONNECTION_LOG.info(msg);
    }
}
