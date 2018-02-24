/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.servicecomb.metrics.core.event;

import org.apache.servicecomb.core.metrics.InvocationStartExecutionEvent;
import org.apache.servicecomb.foundation.common.event.EventListener;
import org.apache.servicecomb.foundation.metrics.MetricsConst;
import org.apache.servicecomb.metrics.core.MonitorManager;
import org.apache.servicecomb.swagger.invocation.InvocationType;

public class InvocationStartExecutionEventListener implements EventListener<InvocationStartExecutionEvent> {
  @Override
  public Class<InvocationStartExecutionEvent> getEventClass() {
    return InvocationStartExecutionEvent.class;
  }

  @Override
  public void process(InvocationStartExecutionEvent data) {
    MonitorManager.getInstance().getCounter(MetricsConst.SERVICECOMB_INVOCATION,
        MetricsConst.TAG_OPERATION, data.getOperationName(),
        MetricsConst.TAG_STAGE, MetricsConst.STAGE_QUEUE,
        MetricsConst.TAG_ROLE, String.valueOf(InvocationType.PRODUCER).toLowerCase(),
        MetricsConst.TAG_STATISTIC, "waitInQueue").increment(-1);
  }
}