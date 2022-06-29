install-norm-codegen:
	mkdir -p ".gradle"
	curl -L https://github.com/medly/norm/releases/download/v0.0.5/norm-codegen.zip --output ./.gradle/norm-codegen.zip
	unzip -o ./.gradle/norm-codegen.zip -d ./.gradle && rm ./.gradle/norm-codegen.zip
	chmod +x .gradle/norm-codegen/bin/norm-codegen

norm-codegen:
	./gradlew migration:run
	@git diff --name-only -- "connector/db/sql/**.sql" | xargs .gradle/norm-codegen/bin/norm-codegen -b connector/db/sql -o connector/db/gen -j "jdbc:postgresql://localhost/blog_dev" -u "postgres"
