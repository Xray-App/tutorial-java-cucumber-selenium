FROM maven:3.8.1-adoptopenjdk-11 AS build

RUN apt-get update && \
    apt-get install -y gnupg wget curl unzip --no-install-recommends && \
    wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - && \
    echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
    apt-get update -y && \
    apt-get install -y google-chrome-stable && \
    CHROMEVER=$(google-chrome --product-version | grep -o "[^\.]*\.[^\.]*\.[^\.]*") && \
    DRIVERVER=$(curl -s "https://chromedriver.storage.googleapis.com/LATEST_RELEASE_$CHROMEVER") && \
    wget -q --continue -P /chromedriver "http://chromedriver.storage.googleapis.com/$DRIVERVER/chromedriver_linux64.zip" && \
    unzip /chromedriver/chromedriver* -d /chromedriver

# Create a default user
RUN groupadd --system automation && \
    useradd --system --create-home --gid automation  automation && \
    chown --recursive automation:automation /home/automation


RUN apt-get update && apt-get install -y unzip

WORKDIR /source

COPY features/ ./features/
COPY *.xml .
COPY *.json .
COPY *.sh .
COPY src/ ./src/

RUN chown -R automation.automation /source
USER automation

RUN mvn dependency:resolve

ENV PATH $PATH:/home/automation/.local/bin

# Put Chromedriver into the PATH
ENV PATH /chromedriver:$PATH
# set display port to avoid crash
ENV DISPLAY=:99


#ENTRYPOINT ["mvn", "clean", "compile", "test", "-Dcucumber.plugin=json:report.json", "-Dcucumber.features=features/"]
ENTRYPOINT ["./run_all_cloud_standard_workflow.sh"]

