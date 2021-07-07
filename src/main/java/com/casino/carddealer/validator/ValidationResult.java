package com.casino.carddealer.validator;

import java.util.List;
import java.util.Set;

/**
 * Simply a pair to store the validation error codes, and the list of the given cards.
 */
public class ValidationResult {

  private Set<ErrorCodes> errorCodes;
  private List<String> cards;

  public ValidationResult(Set<ErrorCodes> errorCodes, List<String> cards) {
    this.errorCodes = errorCodes;
    this.cards = cards;
  }

  public Set<ErrorCodes> getErrorCodes() {
    return errorCodes;
  }

  public List<String> getCards() {
    return cards;
  }

}
