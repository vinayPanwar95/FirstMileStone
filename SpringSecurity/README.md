#Spring Security project
This service has implemented spring securities.

While implementing spring securities, pom dependecies versio play a major role, all jars(spring boot starter parent , security starter, Security Config) must be compatible.

#Scenrio Covered: 
Spring securities work on security filters and we can add custom filters in between to achive our business security. 

Consider this service is being called from UI as well as from any other service also and we have to run this locally also in 
1) Called from UI, we will get the token which will have our Context User Details 
2) Called from another rest service and this service will pass secruty header which has all details required for making ContextUser
3) Run Locally then it will take only one header (tNumber) and all the other properties required to create ContextUser will load from application.properties file

in Controller we have used @PReAuthorize which will check the roles before hitting controller method