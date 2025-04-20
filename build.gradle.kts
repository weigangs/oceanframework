import conventions.PublishingConvention

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}


subprojects {
    apply<PublishingConvention>()
}