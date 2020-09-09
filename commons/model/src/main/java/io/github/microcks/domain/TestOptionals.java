/*
 * Licensed to Laurent Broudoux (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.github.microcks.domain;

import java.util.List;

/**
 * Simple bean representing the optional elements of a Test requests. Some of them are made
 * to be persisted into TestResult, some other are just volatile information for execution.
 * @author laurent
 */
public class TestOptionals {

   private String secretId;
   private Long timeout;
   private List<String> filteredOperations;
   private OperationsHeaders operationsHeaders;

   public TestOptionals() {
   }

   public TestOptionals(String secretId, Long timeout, List<String> filteredOperations, OperationsHeaders operationsHeaders) {
      this.secretId = secretId;
      this.timeout = timeout;
      this.filteredOperations = filteredOperations;
      this.operationsHeaders = operationsHeaders;
   }

   public String getSecretId() {
      return secretId;
   }

   public void setSecretId(String secretId) {
      this.secretId = secretId;
   }

   public Long getTimeout() {
      return timeout;
   }

   public void setTimeout(Long timeout) {
      this.timeout = timeout;
   }

   public List<String> getFilteredOperations() {
      return filteredOperations;
   }

   public void setFilteredOperations(List<String> filteredOperations) {
      this.filteredOperations = filteredOperations;
   }

   public OperationsHeaders getOperationsHeaders() {
      return operationsHeaders;
   }

   public void setOperationsHeaders(OperationsHeaders operationsHeaders) {
      this.operationsHeaders = operationsHeaders;
   }
}