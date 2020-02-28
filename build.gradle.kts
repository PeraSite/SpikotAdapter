group = "kr.heartpattern.spikot"
version = "1.0-SNAPSHOT"

// For faster build in jenkins ci
if("nexusUser" in properties && "nexusPassword" in properties){
    allprojects{
        repositories{
            maven("https://maven.heartpattern.kr/repository/minecraft-snapshots/"){
                credentials{
                    username = project.properties["nexusUser"].toString()
                    password = project.properties["nexusPassword"].toString()
                }
            }
        }
    }
}
