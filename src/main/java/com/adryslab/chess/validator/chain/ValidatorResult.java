package com.adryslab.chess.validator.chain;

import com.adryslab.chess.model.Result;

import java.util.ArrayList;
import java.util.List;

/*
 * Wraps the evaluator chain message result.
 * it saves all failed messages within the Chain of validators.
 */
public class ValidatorResult<T> {

    private final List<Result<T>> evaluatorMessages = new ArrayList<>();
    private boolean hasFailures = false;

    void addEvaluatorMessage(final Result<T> validatorResult) {
        if (validatorResult.isFailure()) {
            evaluatorMessages.add(validatorResult);
            hasFailures = true;
        }
    }

    public List<Result<T>> getEvaluatorMessages() {
        return evaluatorMessages;
    }

    public boolean hasMessages() {
        return hasFailures;
    }
}
