package com.casino.carddealer.comparator;

import com.casino.carddealer.evaluator.Evaluation;
import java.util.Comparator;
import java.util.List;

/**
 * Evaluation comparator is used to sort a set of evaluations to decide winners and the rank for the
 * game. The order of the sorting as follows: first rank hierarchy is compared then their highest
 * values. Great values are promoted since evaluation aims to find a winner with the best hand.
 */
public class EvaluationComparator implements Comparator<Evaluation> {

  @Override
  public int compare(Evaluation e1, Evaluation e2) {
    if (e1.getRank().getHierarchy() > e2.getRank().getHierarchy()) {
      return -1;
    } else if (e1.getRank().getHierarchy() < e2.getRank().getHierarchy()) {
      return 1;
    } else {
      int result = compareValues(e1.getHighestValue(), e2.getHighestValue());
      if (result == 0) {
        e2.setEqualPrevious(true);
        result = -1;
      }

      return result;
    }
  }

  private int compareValues(List<Integer> highestValue1, List<Integer> highestValue2) {
    for (var index = 0; index < highestValue1.size(); index++) {
      Integer value = highestValue1.get(index);
      Integer thatValue = highestValue2.get(index);

      if (value > thatValue) {
        return -1;
      } else if (value < thatValue) {
        return 1;
      }
    }

    return 0;
  }

}
