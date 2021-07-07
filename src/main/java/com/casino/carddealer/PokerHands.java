/*
 *  Copyright 2021-present the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.casino.carddealer;

import com.casino.carddealer.evaluator.GameEvaluator;
import com.casino.carddealer.utility.LoggerHelper;
import com.casino.carddealer.validator.InputValidator;
import com.casino.carddealer.validator.ValidationResult;
import java.util.logging.Logger;

/**
 * Provides the entry point for running the application from the command line.
 */
public class PokerHands {

  private static final Logger log = LoggerHelper.createLogger(PokerHands.class.getName());

  public static void main(String[] args) {
    // Before evaluation of the poker hands, the given hands are validated.
    ValidationResult vr = InputValidator.validateHands(args);

    /* In case of one or more issues are detected with validation
     * they are printed on the console by the logger and the program ends.
     */
    if (!vr.getErrorCodes().isEmpty()) {
      vr.getErrorCodes().forEach(ec -> log.severe(ec.toString()));
    } else {
      // Game evaluator process the hands and prints out the result.
      var ge = new GameEvaluator(vr.getCards());
      ge.evaluate();
    }
  }

}
