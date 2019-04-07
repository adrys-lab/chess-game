package com.adryslab.chess.model.annotation;

import java.lang.annotation.*;

/**
 * Immutable
 *
 * Immutable objects are inherently thread-safe; they may be passed between
 * threads or published without synchronization.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Immutable {
}
