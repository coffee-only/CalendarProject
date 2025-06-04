package api; 

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application{
  public static void main(String[] args){
    SpringApplication.run(Application.class, args);
  }
      @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void logDatabaseInfo() {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("ACTUAL JDBC URL     : " + conn.getMetaData().getURL());
            System.out.println("ACTUAL DB Driver    : " + conn.getMetaData().getDriverName());
            System.out.println("ACTUAL DB Version   : " + conn.getMetaData().getDatabaseProductVersion());
        } catch (Exception e) {
            System.err.println("Failed to log DB metadata: " + e.getMessage());
        }
    }
}
