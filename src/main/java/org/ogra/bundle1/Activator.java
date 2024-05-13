package org.ogra.bundle1;


/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import java.util.Map;

public class Activator implements BundleActivator, PallindromeService {
    private ServiceRegistration serviceReg;
    private EventAdmin eventAdmin;

    public void start(BundleContext context) {
        System.out.println("Starting the bundle1");
        serviceReg = context.registerService(PallindromeService.class.getName(), this, null);

        eventAdmin = context.getService(context.getServiceReference(EventAdmin.class));
        // Publish an event indicating service started
        publishEvent("org/ogra/bundle1/serviceStarted");

    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle1");
        publishEvent("org/ogra/bundle1/serviceStopping");
    }

    private void publishEvent(String topic) {
        if (eventAdmin != null) {
            Event event = new Event(topic, (Map<String, ?>) null);
            eventAdmin.postEvent(event);
        }
    }

    public boolean isPallindrome(String a) {
        int n = a.length();
        String b = "";
        for (int i = n - 1; i >= 0; i--) {
            b = b + a.charAt(i);
        }
        return b.equalsIgnoreCase(a);
    }
}