package org.ogra.bundle2;
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
import org.ogra.bundle1.PallindromeService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class Activator implements BundleActivator, EventHandler {

    private ServiceReference serviceReg;
    private ServiceRegistration<?> eventHandler;


    public void start(BundleContext context) {
        System.out.println("Starting the bundle2");
        serviceReg = context.getServiceReference(PallindromeService.class.getName());
        PallindromeService ms = (PallindromeService) context.getService(serviceReg);
        boolean b = ms.isPallindrome("abccba");
        System.out.println(b);

        eventHandler = context.registerService(EventHandler.class.getName(), new EventHandler() {
            @Override
            public void handleEvent(Event event) {
                String topic = event.getTopic();
                System.out.println("Received event with topic: " + topic);
            }
        }, null);
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping the bundle2");
    }

    @Override
    public void handleEvent(Event event) {
        // Handle the received event
        String topic = event.getTopic();
        System.out.println("Received event with topic: " + topic);
        // Add your custom logic here based on the event topic
    }

}