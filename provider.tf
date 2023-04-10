terraform {
  required_providers {
    github = {
      source  = "integrations/github"
      version = "~> 5.0"
    }
  }
}

provider "github" {
  token = "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"
}
