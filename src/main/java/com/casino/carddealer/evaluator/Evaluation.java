package com.casino.carddealer.evaluator;

import java.util.List;

/**
 * Simply it is used to store the hand, the highest value of the hand, and the evaluation result for
 * a player.
 */
public class Evaluation {

  private String player;
  private Ranks rank;
  private String hand;
  private String handOf;
  private List<Integer> highestValue;
  private boolean equalPrevious;

  private Evaluation() {
    // private constructor for builder
  }

  public String getPlayer() {
    return player;
  }

  public Ranks getRank() {
    return rank;
  }

  public String getHand() {
    return hand;
  }

  public String getHandOf() {
    return handOf;
  }

  public List<Integer> getHighestValue() {
    return highestValue;
  }

  public boolean isEqualPrevious() {
    return equalPrevious;
  }

  public void setEqualPrevious(boolean equalPrevious) {
    this.equalPrevious = equalPrevious;
  }

  public static final class Builder {

    private final String player;
    private Ranks rank;
    private String hand;
    private String handOf;
    private List<Integer> highestValue;

    public Builder(String player) {
      this.player = player;
    }

    public Builder withRank(Ranks rank) {
      this.rank = rank;

      return this;
    }

    public Builder withHand(String hand) {
      this.hand = hand;

      return this;
    }

    public Builder withHandOf(String handOf) {
      this.handOf = handOf;

      return this;
    }

    public Builder withHighestValue(List<Integer> highestValue) {
      this.highestValue = highestValue;

      return this;
    }

    public Evaluation build() {
      var evaluation = new Evaluation();
      evaluation.player = this.player;
      evaluation.rank = this.rank;
      evaluation.hand = this.hand;
      evaluation.handOf = this.handOf;
      evaluation.highestValue = this.highestValue;

      return evaluation;
    }
  }

}
