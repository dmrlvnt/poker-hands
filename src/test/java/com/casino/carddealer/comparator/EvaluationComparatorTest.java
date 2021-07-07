package com.casino.carddealer.comparator;

import static org.assertj.core.api.Assertions.assertThat;

import com.casino.carddealer.evaluator.Evaluation;
import com.casino.carddealer.evaluator.Ranks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Evaluation comparator {@link EvaluationComparator} test class.
 */
class EvaluationComparatorTest {

  @DisplayName("Two evaluations sorted with comparator")
  @Test
  void testTwoEvaluations() {
    // Given
    Evaluation ev1 = new Evaluation.Builder("Player 1").withRank(Ranks.FLUSH)
        .withHand("3D6D7DTDQD")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(12, 10, 7, 6, 3))).build();
    Evaluation ev2 = new Evaluation.Builder("Player 2").withRank(Ranks.FLUSH)
        .withHand("3D6D7DTDAD")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(14, 10, 7, 6, 3))).build();
    List<Evaluation> evaluations = new ArrayList<Evaluation>(Arrays.asList(ev1, ev2));

    // When
    evaluations.sort(new EvaluationComparator());

    // Then
    assertThat(evaluations.size()).isSameAs(2);
    assertThat(evaluations.get(0)).isSameAs(ev2);
    assertThat(evaluations.get(1)).isSameAs(ev1);
  }

  @DisplayName("Three evaluations sorted with comparator")
  @Test
  void testThreeEvaluations() {
    // Given
    Evaluation ev1 = new Evaluation.Builder("Player 1").withRank(Ranks.FLUSH)
        .withHand("3D6D7DTDQD")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(12, 10, 7, 6, 3))).build();
    Evaluation ev2 = new Evaluation.Builder("Player 2").withRank(Ranks.FLUSH)
        .withHand("3D6D7DTDAD")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(14, 10, 7, 6, 3))).build();
    Evaluation ev3 = new Evaluation.Builder("Player 3").withRank(Ranks.STRAIGHT_FLUSH)
        .withHand("3D4D5D6D7D")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(7, 6, 5, 4, 3))).build();
    List<Evaluation> evaluations = new ArrayList<Evaluation>(Arrays.asList(ev1, ev2, ev3));

    // When
    evaluations.sort(new EvaluationComparator());

    // Then
    assertThat(evaluations.size()).isSameAs(3);
    assertThat(evaluations.get(0)).isSameAs(ev3);
    assertThat(evaluations.get(1)).isSameAs(ev2);
    assertThat(evaluations.get(2)).isSameAs(ev1);
  }

  @DisplayName("Four evaluations sorted with comparator")
  @Test
  void testFourEvaluations() {
    // Given
    Evaluation ev1 = new Evaluation.Builder("Player 1").withRank(Ranks.FLUSH)
        .withHand("3D6D7DTDQD")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(12, 10, 7, 6, 3))).build();
    Evaluation ev2 = new Evaluation.Builder("Player 2").withRank(Ranks.FLUSH)
        .withHand("3D6D7DTDAD")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(14, 10, 7, 6, 3))).build();
    Evaluation ev3 = new Evaluation.Builder("Player 3").withRank(Ranks.STRAIGHT_FLUSH)
        .withHand("3D4D5D6D7D")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(7, 6, 5, 4, 3))).build();
    Evaluation ev4 = new Evaluation.Builder("Player 3").withRank(Ranks.FOUR_OF_A_KIND)
        .withHand("8C8D8H8S2D")
        .withHandOf("Diamonds")
        .withHighestValue(new ArrayList<Integer>(Arrays.asList(7, 6, 5, 4, 3))).build();
    List<Evaluation> evaluations = new ArrayList<Evaluation>(Arrays.asList(ev1, ev2, ev3, ev4));

    // When
    evaluations.sort(new EvaluationComparator());

    // Then
    assertThat(evaluations.size()).isSameAs(4);
    assertThat(evaluations.get(0)).isSameAs(ev3);
    assertThat(evaluations.get(1)).isSameAs(ev4);
    assertThat(evaluations.get(2)).isSameAs(ev2);
    assertThat(evaluations.get(3)).isSameAs(ev1);
  }


}
