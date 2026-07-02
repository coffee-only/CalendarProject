{
      description = "flake for kotlin/java devellopement";

      inputs = {
            nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
      };

      outputs = { self, nixpkgs }:
      let 
            system = "x86_64-linux";
            pkgs = import nixpkgs {
                  inherit system;
                  config.allowUnfree = true; # for intellij
            };

            myJdk  = pkgs.openjdk21;
            gradle = pkgs.gradle.override { java = myJdk; };
            kotlin = pkgs.kotlin.override { jre  = myJdk; };
      in {
            devShells.${system}.default = pkgs.mkShell {
                  packages = [
                        pkgs.jetbrains.idea-oss
                        pkgs.spring-boot-cli
                        myJdk
                        kotlin
                        gradle

                        pkgs.docker
                        pkgs.docker-compose
                  ];
                  shellHooks = ''
                        export JAVA_HOME="${myJdk.home}"
                        export GRADLE_USER_HOME="$PWD/.gradle"
                   '';
            }; 
      };
}
