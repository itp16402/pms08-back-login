package org.pms.samlogin.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Authority {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "role")
	private String description;
}
