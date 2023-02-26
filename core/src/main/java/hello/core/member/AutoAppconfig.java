package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class AutoAppconfig {

	@Configuration
	@ComponentScan(
			excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
	)
	public class AutoAppConfig {

	}

}
