/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 * @author James Dickson
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import static com.automationanywhere.commandsdk.model.AttributeType.CREDENTIAL;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.STRING;
import com.automationanywhere.botcommand.samples.Utils.SlackServer;
import java.io.IOException;
import java.util.Map;
import com.automationanywhere.commandsdk.annotations.BotCommand;
import com.automationanywhere.commandsdk.annotations.CommandPkg;
import com.automationanywhere.commandsdk.annotations.Execute;
import com.automationanywhere.commandsdk.annotations.Idx;
import com.automationanywhere.commandsdk.annotations.Pkg;
import com.automationanywhere.commandsdk.annotations.Sessions;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.core.security.SecureString;
import com.slack.api.Slack;
import org.json.simple.parser.ParseException;

/**
 * @author James Dickson
 */
@BotCommand
@CommandPkg(label = "Start Session",
		description = "Starts Session with OAuth Bot Token",
		icon = "SLACK.svg",
		name = "startSlackSession",
		node_label = "Start Session {{sessionName}}",
		group_label="Admin",
		comment = true
		)
public class A_StartSession {

	@Sessions
	private Map<String, Object> sessionMap;

	@Execute
	public void execute(
			@Idx(index = "1", type = TEXT) @Pkg(label = "Session name",  default_value_type = STRING, default_value = "Default") @NotEmpty String sessionName,
			@Idx(index = "2", type = CREDENTIAL) @Pkg(label = "Token") @NotEmpty SecureString token
	) {
		Slack instance = Slack.getInstance();
		SlackServer slackToken = new SlackServer(token.getInsecureString(), instance);
		this.sessionMap.put(sessionName, slackToken);
	}

	public void setSessionMap(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

}
