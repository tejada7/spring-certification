package com.ftm.vcp.bootexamples.infrastructure.driven.jdbc;

import com.ftm.vcp.bootexamples.infrastructure.driven.jdbc.entity.FooEntity;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<FooEntity, String> {
}
