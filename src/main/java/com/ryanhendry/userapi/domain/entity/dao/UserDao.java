package com.ryanhendry.userapi.domain.entity.dao;

import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "user")
public class UserDao {
  @Id
  @GeneratedValue(generator="uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private UUID id;
  private String username;
  private String password;
  private LocalDate dob;
  private String pan;

  public UserDao(UUID id, String username, String password, LocalDate dob, String pan) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.dob = dob;
    this.pan = pan;
  }

  public UserDao() {
  }

  public UUID getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getDob() {
    return dob;
  }

  public String getPan() {
    return pan;
  }
}
