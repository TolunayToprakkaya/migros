package com.project.migros.entity;

import com.project.migros.base.type.AbstractEditableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cour")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourierEntity extends AbstractEditableEntity {

  @Id
  @Column(name = "cour_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long courId;

  @Basic
  @Column(name = "arrvl_time")
  private Date arrvlTime;

  @Basic
  @Column(name = "id_num")
  private Long idNum;

  @Basic
  @Column(name = "name")
  private String name;

  @Basic
  @Column(name = "town")
  private String town;

  @Basic
  @Column(name = "lat")
  private Double lat;

  @Basic
  @Column(name = "lng")
  private Double lng;

  @Basic
  @Column(name = "dstnc")
  private Double dstnc;

  @Basic
  @Column(name = "lst_str")
  private String lstStr;
}
