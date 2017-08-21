package config;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.ConfigurationCondition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(ConditionalOnConsulEnabled.OnConsulEnabledCondition.class)
public @interface ConditionalOnConsulEnabled {

    class OnConsulEnabledCondition extends AllNestedConditions {

        public OnConsulEnabledCondition() {
            super(ConfigurationCondition.ConfigurationPhase.REGISTER_BEAN);
        }

        @ConditionalOnProperty(value = "rpc.consul.enabled", matchIfMissing = true)
        static class FoundProperty {}

        @ConditionalOnClass(ConsulClient.class)
        static class FoundClass {}
    }
}
