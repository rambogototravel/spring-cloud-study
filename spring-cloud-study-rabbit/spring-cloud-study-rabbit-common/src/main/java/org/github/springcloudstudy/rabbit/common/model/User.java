package org.github.springcloudstudy.rabbit.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author yangruibo
 */
@Getter
@Setter
@Accessors(chain = true)
public class User {

    private String username;

    private int age;
}
