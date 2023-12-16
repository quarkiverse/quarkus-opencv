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
package io.quarkiverse.opencv.it;

import java.io.InputStream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Net;

@Path("/opencv")
@ApplicationScoped
public class OpenCVResource {

    @GET
    @Path("/basic")
    @Produces(MediaType.TEXT_PLAIN)
    public int basicCheck() {
        Mat zeros = Mat.zeros(10, 10, CvType.CV_8UC1);
        return zeros.dims();
    }

    @GET
    @Path("/dnn")
    @Produces(MediaType.TEXT_PLAIN)
    public String testDnn() throws Exception {
        Net net = Dnn.readNetFromCaffe(readModel("models/pose/coco/pose_deploy_linevec.prototxt"));

        return net.getClass().getName();
    }

    private static MatOfByte readModel(String fileName) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            // In a real application, either read in chunk or read the model from the disk
            // Note that the OpenCV library won't be able to read files from the class loader as it is too level
            return new MatOfByte(is.readAllBytes());
        }
    }
}
