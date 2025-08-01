{
  description = "A very basic flake";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-unstable";
  };

  outputs = { self, nixpkgs }: {

    devShells.x86_64-linux.default = nixpkgs.legacyPackages.x86_64-linux.mkShell {
      buildInputs = [
        nixpkgs.legacyPackages.x86_64-linux.openjdk
        nixpkgs.legacyPackages.x86_64-linux.gradle
      ];

      shellHook = ''
        export PS1="(nix-shell) $PS1"
      '';
    };
  };
}
