//package br.com.exemplo.forum.config.swagger;

//@Configuration
//public class SwaggerConfigurations {
//	 
//	    @Bean
//	    public Docket api() {
//	        return new Docket(DocumentationType.SWAGGER_2)
//	                .select()
//	                .apis(RequestHandlerSelectors.basePackage("br.com.exemplo.forum"))
//	                .paths(PathSelectors.ant("/**"))
//	                .build()
//	                .ignoredParameterTypes(Usuario.class)
//	                .globalOperationParameters(
//	                        Arrays.asList(
//	                                new ParameterBuilder()
//	                                    .name("Authorization")
//	                                    .description("Header para Token JWT")
//	                                    .modelRef(new ModelRef("string"))
//	                                    .parameterType("header")
//	                                    .required(false)
//	                                    .build()));
//	    }
//}
