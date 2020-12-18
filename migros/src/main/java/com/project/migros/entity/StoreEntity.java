package com.project.migros.entity;

import com.project.migros.base.type.AbstractEditableEntity;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "str")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreEntity extends AbstractEditableEntity {

  @Id
  @Column(name = "str_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long strId;

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
}
