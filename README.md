# basic-springboot-2024
Java 빅데이터 개발자 과정 Spring Boot 학습 리포지토리

## 1일차
- Spring Boot 개요
    - 개발환경, 개발 난이도를 낮추는 작업
    - Servlet > EJB > JSP > Spring(부흥기) > Spring Boot(끝판왕!!)
    - 장점
        - Spring 의 기술은 그대로 사용가능(마이그레이션 간단)
        - JPA를 사용하면 ERD나 DB설계를 하지 않고도 손쉽게 DB 생성
        - Tomcat Webserver가 내장(따로 설치필요x)
        - 서포트 기능 다수 존재(개발을 쉽게 도와줌)
        - JUnit 테스트, Log32 로그도 모두 포함
        - JSP, **Thymeleaf**, Mustache 등.. 편하게 사용가능
        - DB 연동이 무지 쉽다

        - MVC
            <img src="https://github.com/YoonChanWo0/basic-springboot-2024/main/images/sp002.png" width="730">

- Spring Boot 개발환경 설정
    - Java JDK 확인 > 17버전 이상
    - Visual Studio Code
        - VsCodeUserSetUp-x64-1.90.0.exe가 아님
        - VsCodeSetUp으로 설치
        - Extensions > 한국어 검색해서 설치
        - Extensions > Java 검색해서 Extention Pack for Java 설치
        - Extensions > Spiring 검색해서 Spring Extention Pack 설치
        - Extensions > Gradle for Java 검색해서 설치
    - Gradle build tool 설치고려(필요 시)
        - https://gradle.org/releases/
    - Oracle latest version Docker
    - Node.js
    - React setting

- Spring Boot 프로젝트 생성
    - 보기 > 명령 팔레트(ctrl + shift + p)
        - Spring Initializr : Create a Gradle Project
        - Specify Spring Boot version : 3.2.6
        - Specify project language : Java
        - Input Group Id : com.(임의로 변경)
        - Input Artifact Id (대문자 불가능)
        - Specify package type : Jar
        - Specify Java version : 17
        - Choose dependencies : Selected 0 dependencies
        - 폴더 선택 Diaglog 팝업 : 원하는 폴더 선택 후 Generate
        - 오른쪽 하단 팝업에서 Open 
        - Git 설정 옵션, Language Support for Java by Red Hat 설정 '항상'버튼 클릭
    
    - TroubleShooting
        1. 프로젝트 생성이 진행되다가 Gradle Connection 에러가 뜨면
            - Extentions > Gradle for Java를 제거 후
            - VsCode를 재시작한 뒤 프로젝트를 재 생성
        2. Gradle 빌드 시 버전 에러로 빌드가 실패하면
            - Gradle build tool 사이트에서 에러에 표시도니 버전의 Gradle bt 다운
            - 개발 컴퓨터에 설치
        3. ':compileJava' excution failed...
            - JDK 17 .... ERROR 메시지
            - Java JDK 잘못된 설치 x86(32bit) x64비트 혼용 설치
            - eclipse adoptium jdk 17 설치
    
    - 프로젝트 생성 후 
        - build.gradle 확인
        - src/main/resources/application.properties(또는 .yml) 확인
        - src/Java/GroupID/ArtifactID/ Java 소스파일 위치, 작업
        - src/main/resources/ 프로젝트 설정파일, 웹 리소스 파일(CSS, HTML, JS, JSP ... )
        - Spring01Application.java Run|Debug 메뉴
            - 터미널에서 .\gradlew.bat 실행
            - Gradle for java(코끼리 아이콘) > Tasks > Build > Build play icon(Run task) 실행
            - Spring Boot Dashboard
                - App > Spring01 run | Debug 중에서 하나 아이콘 클릭 서버 실행
                - 디버그로 실행해야 Hot code replace가 동작

                 <img src="https://github.com/YoonChanWo0/basic-springboot-2024/main/images/sp001.png" width="730">

                - 브라우저 변경설정
                    - 설정(ctrl + ,) > browser > Spring > Dashbord Open With 'Internal' -> 'external'로 변경
                    - Chrome 을 기본 브라우저 사용 추천
                    
## 2일차
    - Oracle 도커로 설치
        - Docker는 Virtual Machine을 업그레이드한 시스템
        - 윈도우 서비스 내(services.msc) Oracle을 서비스 종료
        - Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
        - Docker 설치시 오류 Docker Desktop - MSL Update failed
            - Docker Desktop 실행종료 후
            - Windows 업데이트 실행 최신판
            - https://github.com/microsoft/WSL/releases wsl.2.x.x.x64.msi 다운로드 설치 한 뒤
            - Docker Desktop 재실행
        - Oracle 최신판 설치
        ```
        > docker --version
        > docker pull container-registry.oracle.com/database/free:latest
        latest: ....
        ... : Download complete
        >docker images
        REPOSITORY                                    TAG       IMAGE ID       CREATED       SIZE
        container-registry.oracle.com/database/free   latest    7510f8869b04   7 weeks ago   8.7GB
        > docker run -d -p 1521:1521 --name oracle container-registry.oracle.com/database/free
        ....
        > docker logs oracle
        ```    
        - Oracle system 사용자 비번 설정
        ```shell
        bash-4.4$ ./setPassword.sh oracle
        ```

        - Oracle 접속확인
            - Dbeaver 탐색기 > Create > Connection

    - Database 설정
        - Oracle - 운영시 사용할 DB
        - Oracle PKNUSB / pknu_p@ss 로 생성
        - 콘솔(도커 / 일반 Oracle)
        ``` shell
        > sqlplus system/password
        SQL> select name from v$database;
        // 서비스명 확인
        // 최신버전에서 사용자 생성시 C## prefix 방지 처리
        SQL> ALTER SESSION SET "_ORACLE_SCRIPT"=true;
        // 사용자 생성
        SQL> create user pknusb identified by "pknu_p@ss";
        // 사용자 권한
        SQL> grant CONNECT, RESOURCE, CREATE SESSION, CREATE TABLE, CREATE SEQUENCE, CREATE VIEW to pknusb; 
        // 사용자 계정 테이블 공간설정, 공간쿼터
        SQL> alter user pknusb default tablespace users;
        SQL> alter user pknusb quota unlimited on users;
        ```
        - H2 DB  - Spring boot에서 손쉽게 사용한 Inmemory DB, Oracle, Mysql, SQLServer과 쉽게 호환
        - MYSQL - Optional 설명할 DB

- Spring Boot + MyBatis
    - application name : spring02
    - springboot 3.3.x에는 mtbatis 없음
    - 3.2.6 선택 -> java -> com.cw99 -> spring02 -> jar -> 17 -> 6개 선택
    - Dependency
        - Spring Boot DevTools
        - Spring Web
        - Thymeleaf
        - Oracle Driver
        - MyBatis Driver
        - Mybatis starter
        - Lombok

    - Dependency 중 DB(H2, Oracle, MYSQL) 가 선택되어 있으면 웹서버 실행이 안됨. application.properties에 DB설정 안되면 서버 실행 안됨
        
    - build.gradle확인
     - application.properties 추가작성

    ```properties

       spring.application.name=spring02

    ## 포트변경
    server.port=8091

    ## 로그생상
    spring.output.ansi.enabled=always

    ## 수정사항이 있으면 서버 자동 재빌드 설정
    spring.devtools.livereload.enabled=true
    spring.devtools.restart.enabled=true

    ## 로그레벨 설정
    logging.level.org.springframework=info
    logging.level.org.zerock=debug

    ## Oracle 설정
    spring.datasource.username=pknusb
    spring.datasource.password=pknu_p@ss
    ## spring.datasource.url=jdbc:oracle:thin@localhost:1521:XE 로컬
    spring.datasource.url=jdbc:oracle:thin@localhost:11521:FREE
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

    ## MyBatis 설정
    ## mapper 폴더 밑에 여러가지 폴더가 내재, 확장자는 .xml이지만 파일명은 뭐든지
    mybatis.mapper-locations=classpath:mapper/**/*.xml
    mybatis.type-aliases-package=com.cw98.spring02.mapper
            
    ```

    - MyBatis 적용
        - Spring, resource/WEB-INF 위치에 root-context.xml에 DB, Mybatis 설정
        - SpringBoot 이후 application.properties + Config.java로 변경

    - MyBatis 개발시 순서
        0. application.properties jdbc:oracle:thin:@localhost:1521:FREE , thin뒤 :이 삭제되어 있었음
        1. Database 테이블 생성
        2. MyBatis 설정 -> /config/MyBatisConfig.java
        3. 테이블과 일치하는 클래스(domain, entity, dto, vo, ...) 생성
            - 테이블 컬럼 _는 Java클래스는 사용 안함
        4. DB에 데이터를 주고받을 수 있는 클래스 (dao, **mapper**, repository ...) 생성
            - 쿼리를 클래스내 작성가능, xml로 분리 가능
        5. (Model) 분리했을 경우 /resources/mapper/클래스.xml 생성, 쿼리 입력
        6. 서비스 인터페이스 /service/*Service.java, 서비스 구현한 클래스 /service/*serviceImpl.java 생성 작성
        7. 사용자 접근하는 컨트롤러 @RestController 클래스 생성 -> @Controller 변경 가능
        8. (Controller)경우에 따라 @SpringBootApplication 클래스에 SqlSessionFactory 빈을 생성 메서드 작성
        9. (View) /resource/templates/ Thymeleaf html 생성, 작성