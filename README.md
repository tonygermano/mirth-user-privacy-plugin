# A Mirth Connect Privacy Plugin

This plugin for [Mirth Connect](https://github.com/nextgenhealthcare/connect) performs two
functions to prevent exposing details of your server and
[Personal Data](https://en.wikipedia.org/wiki/Personal_data) to NextGen Healthcare.

First, it disables the collection of server and client statistics. There is a checkbox in the
server settings which allows you to opt-out of this data collection. However, it defaults to
being on, and there is typically no way to prevent the initial sending of this data the first
time spinning up a new server. The plugin turns this option off before the first time the server
performs the check to see whether to send or not.

Second, it bypasses the first
time login screen. While previous versions of Mirth allowed you to skip registration and filling
out the form, as of version 4.1.0 it will not let you proceed without filling out the form and
submitting it to NextGen. Although there is a checkbox to opt-out of receiving email communication
from NextGen, the application still will not allow you past this form without every field populated.
This is an immense burden for developers during testing when new server instances are frequently
created and thrown away. Ironically, this intrusive behavior is touted as a
[new feature](https://github.com/nextgenhealthcare/connect/wiki/4.1.0---What's-New#welcome-to-mirth-connect).

The plugin accomplishes this by simply setting the firstlogin flag to false for user 1 (`admin`)
which is normally set by the application after completing the first login process.
Be aware that the software will no longer prompt you to change your password, which is typically
not an issue for development servers with a short lifespan for which this plugin is intented to
be used.

This repository is based on [this excellent sample project](https://github.com/kpalang/mirth-sample-plugin).

---

## Build Instruction
1. [Install Java](https://www.javatpoint.com/javafx-how-to-install-java)
1. [Install Maven](https://www.javatpoint.com/how-to-install-maven)
1. Run `git clone https://github.com/tonygermano/mirth-privacy-plugin`
1. Navigate to `mirth-privacy-plugin/`
1. Run `mvn install` to install dependencies to local cache
1. Run `mvn clean package` to verify the build works
1. Get the `.zip` archive from `distribution/target`

---

## Installation
Due to the nature of this plugin it has little use if installed by normal means through the
Administrator Client. Installation therefore depends on how you are running mirth.

- For a typical installation using either the installer or compressed archives available for
download from NextGen's website, you should unzip the contents of the `.zip` archive to the
`extensions` directory in your Mirth installation directory.
- If running a container based on the
[NextGen docker image](https://github.com/nextgenhealthcare/connect-docker), the extension
can be included by:
  1. Mounting the `.zip` archive into the container in the `/opt/connect/custom-extensions`
  directory.
  1. Nest the `.zip` archive within another `.zip` archive containing all additonal extensions
  you would like to run, and then set the `EXTENSIONS_DOWNLOAD` environment variable to point
  to a URL at which you are hosting the file which will be downloaded and extracted at startup.

---

## License
Copyright 2021 Kaur Palang  
Copyright 2022 Tony Germano

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
