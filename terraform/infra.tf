terraform {
  backend "gcs" {
    bucket = "tf-state-gcp-batch-ingestion"
    region = "europe-west1"
    prefix = "terraform/state"
  }
}

provider "google" {
  project = "cloud-proxy-sql-showcase"
  region = "europe-west1"
}

resource "google_storage_bucket" "funky-bucket" {
  name = "batch-pipeline"
  storage_class = "REGIONAL"
  location  = "europe-west1"
}
