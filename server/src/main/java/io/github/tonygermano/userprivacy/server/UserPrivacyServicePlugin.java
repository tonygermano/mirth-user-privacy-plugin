/*
 * Copyright 2021 Kaur Palang
 * Copyright 2022 Tony Germano
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.tonygermano.userprivacy.server;

import com.kaurpalang.mirth.annotationsplugin.annotation.MirthServerClass;
import com.mirth.connect.model.ExtensionPermission;
import com.mirth.connect.plugins.ServicePlugin;
import com.mirth.connect.server.controllers.ControllerFactory;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Properties;

@MirthServerClass
public class UserPrivacyServicePlugin implements ServicePlugin {

    private static final Logger logger = Logger.getLogger(UserPrivacyServicePlugin.class);

    @Override
    public String getPluginPointName() {
        return "User Privacy";
    }

    @Override
    public void start() {
        try {
            logger.debug("Starting up the User Privacy plugin");
            ControllerFactory controllerFactory = ControllerFactory.getFactory();
            controllerFactory.createUserController().setUserPreference(1, "firstlogin", "false");
            controllerFactory.createConfigurationController().saveProperty("core", "stats.enabled", "0");
        } catch (Exception e) {
            logger.error("Error using mirth controllers", e);
        }
    }

    @Override
    public void stop() {}

    @Override
    public Properties getDefaultProperties() {
        return new Properties();
    }

    @Override
    public ExtensionPermission[] getExtensionPermissions() {
        return null;
    }

    @Override
    public Map<String, Object> getObjectsForSwaggerExamples() {
        return null;
    }

    @Override
    public void init(Properties arg0) {}

    @Override
    public void update(Properties arg0) {}
}
