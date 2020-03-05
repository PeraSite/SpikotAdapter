group = "kr.heartpattern.spikot"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenLocal()

        // For faster build in jenkins ci
        if("nexusUser" in properties && "nexusPassword" in properties) {
            maven("https://maven.heartpattern.kr/repository/minecraft-snapshots/") {
                credentials {
                    username = project.properties["nexusUser"].toString()
                    password = project.properties["nexusPassword"].toString()
                }
            }
        }
    }
}
