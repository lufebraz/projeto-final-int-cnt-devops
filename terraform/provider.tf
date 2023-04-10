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
