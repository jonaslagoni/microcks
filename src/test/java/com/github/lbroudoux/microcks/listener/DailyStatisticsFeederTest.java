package com.github.lbroudoux.microcks.listener;

import com.github.lbroudoux.microcks.domain.DailyStatistic;
import com.github.lbroudoux.microcks.event.MockInvocationEvent;
import com.github.lbroudoux.microcks.repository.DailyStatisticRepository;
import com.github.lbroudoux.microcks.repository.RepositoryTestsConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
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
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author laurent
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {RepositoryTestsConfiguration.class, ListenerTestsConfiguration.class})
public class DailyStatisticsFeederTest {

   @Autowired
   DailyStatisticsFeeder feeder;

   @Autowired
   DailyStatisticRepository statisticsRepository;

   @Test
   public void testOnApplicationEvent() {
      Calendar today = Calendar.getInstance();
      MockInvocationEvent event = new MockInvocationEvent(this, "TestService1", "1.0", "123456789", today.getTime(), 100);

      // Fire event a first time.
      feeder.onApplicationEvent(event);

      SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
      String day = formater.format(today.getTime());
      DailyStatistic stat = statisticsRepository.findByDayAndServiceNameAndServiceVersion(day, "TestService1", "1.0");
      assertNotNull(stat);
      assertNotNull(stat.getId());
      assertEquals(day, stat.getDay());
      assertEquals("TestService1", stat.getServiceName());
      assertEquals("1.0", stat.getServiceVersion());
      assertEquals(1, stat.getDailyCount());
      assertEquals(new Integer(1), stat.getHourlyCount().get( String.valueOf(today.get(Calendar.HOUR_OF_DAY)) ));

      // Fire event a second time.
      feeder.onApplicationEvent(event);

      stat = statisticsRepository.findByDayAndServiceNameAndServiceVersion(day, "TestService1", "1.0");
      assertNotNull(stat);
      assertNotNull(stat.getId());
      assertEquals(day, stat.getDay());
      assertEquals("TestService1", stat.getServiceName());
      assertEquals("1.0", stat.getServiceVersion());
      assertEquals(2, stat.getDailyCount());
      assertEquals(new Integer(2), stat.getHourlyCount().get( String.valueOf(today.get(Calendar.HOUR_OF_DAY)) ));
   }
}
