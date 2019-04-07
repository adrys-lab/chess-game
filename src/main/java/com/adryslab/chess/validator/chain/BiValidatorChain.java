package com.adryslab.chess.validator.chain;

import com.adryslab.chess.validator.composed.BiValidator;

import java.util.HashSet;
import java.util.Set;

/*
 * Executes a set of BiValidators in chain and wraps it's result.
 * Evaluators are added into the chain, then evaluated, then the result should be obtained containing all chain list evaluators list of results.
 */
public class BiValidatorChain<I, P, O> {

    private final ValidatorResult<O> validatorResult = new ValidatorResult<>();

    /*
     * Add evaluators as chain based on Set -> no repeat validators.
     */
    private final Set<BiValidator<I, P, O>> evaluatorChain = new HashSet<>();

    public BiValidatorChain<I, P, O> addValidator(final BiValidator<I, P, O> validator) {
        evaluatorChain.add(validator);
        return this;
    }

    public final BiValidatorChain<I, P, O> evaluateAll(final I input, P secondInput) {
        validatorResult.getEvaluatorMessages().clear();
        evaluatorChain
                .forEach(validator -> validatorResult.addEvaluatorMessage(validator.validate(input, secondInput)));

        return this;
    }

    public final ValidatorResult<O> getResult() {
        return validatorResult;
    }


}
