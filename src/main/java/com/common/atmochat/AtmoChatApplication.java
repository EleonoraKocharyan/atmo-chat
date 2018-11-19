//package com.common.atmochat;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//
//@EnableAutoConfiguration(exclude = {
//        SecurityAutoConfiguration.class})
//@Configuration
//@ComponentScan
////@EnableWebSocket
//public class AtmoChatApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(AtmoChatApplication.class, args);
//	}
//}


package com.common.atmochat;

		import org.atmosphere.cpr.AtmosphereServlet;
		import org.atmosphere.cpr.ContainerInitializer;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
		import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
		import org.springframework.boot.web.servlet.ServletContextInitializer;
		import org.springframework.boot.web.servlet.ServletRegistrationBean;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.ComponentScan;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.core.Ordered;
		import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
		import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

		import javax.servlet.ServletContext;
		import javax.servlet.ServletException;
		import java.util.Collections;

@EnableAutoConfiguration(exclude = {
		SecurityAutoConfiguration.class})
@Configuration
@ComponentScan
//@EnableWebSocket
public class AtmoChatApplication {


	@Bean
	public EmbeddedAtmosphereInitializer atmosphereInitializer() {
		return new EmbeddedAtmosphereInitializer();
	}

	@Bean
	public ServletRegistrationBean atmosphereServlet() {
// Dispatcher servlet is mapped to '/home' to allow the AtmosphereServlet
// to be mapped to '/chat'
		ServletRegistrationBean registration = new ServletRegistrationBean(
				new AtmosphereServlet(), "/chat/*");
//		registration.addInitParameter("org.atmosphere.cpr.packages", "sample");
		registration.addInitParameter("org.atmosphere.interceptor.HeartbeatInterceptor"
				+ ".clientHeartbeatFrequencyInSeconds", "10");
		registration.setLoadOnStartup(0);
// Need to occur before the EmbeddedAtmosphereInitializer
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return registration;
	}

//	@Configuration
//	static class MvcConfiguration extends WebMvcConfigurerAdapter {
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//	registry.addViewController("/").setViewName("forward:/home/home.html");
//	}
//
//	}

	private static class EmbeddedAtmosphereInitializer extends ContainerInitializer
			implements ServletContextInitializer {

		@Override
		public void onStartup(ServletContext servletContext) throws ServletException {
			onStartup(Collections.<Class<?>> emptySet(), servletContext);
		}

	}
	public static void main(String[] args) {
		SpringApplication.run(AtmoChatApplication.class, args);
	}
}