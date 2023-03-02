package com.ftm.vcp.bootexamples.infrastructure;

import com.ftm.vcp.bootexamples.infrastructure.entity.Foo;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<Foo, String> {
}
