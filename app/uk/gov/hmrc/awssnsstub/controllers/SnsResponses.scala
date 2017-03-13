/*
 * Copyright 2017 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.awssnsstub.controllers

import java.util.UUID

import uk.gov.hmrc.awssnsstub.controllers.sns.{CreatePlatformEndpoint, PublishRequest}

import scala.xml.Elem

case class CreatePlatformEndpointResponse(endpoint: CreatePlatformEndpoint) {

  val id: String = s"${endpoint.applicationArn}/${endpoint.registrationToken}"
  val arn: String = s"${endpoint.applicationArn}/stubbed/$id"

  def success: Elem = {
    <CreatePlatformEndpointResponse xmlns="http://sns.amazonaws.com/doc/2010-03-31/">
      <CreatePlatformEndpointResult>
        <EndpointArn>{arn}</EndpointArn>
      </CreatePlatformEndpointResult>
      <ResponseMetadata>
        <RequestId>{id}</RequestId>
      </ResponseMetadata>
    </CreatePlatformEndpointResponse>
  }
}

case class PublishRequestResponse(publishRequest: PublishRequest) {

  val rid: String = UUID.randomUUID().toString
  val mid: String = publishRequest.targetArn

  def success: Elem = {
    <PublishResponse xmlns="http://sns.amazonaws.com/doc/2010-03-31/">
      <PublishResult>
        <MessageId>{mid}</MessageId>
      </PublishResult> <ResponseMetadata>
      <RequestId>{rid}</RequestId>
    </ResponseMetadata>
    </PublishResponse>
  }
}