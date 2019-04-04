terraform {
  backend "gcs" {
    bucket = "tf-state-gcp-batch-ingestion-sma"
    region = "europe-west3"
    prefix = "terraform/state"
  }
}

provider "google" {
  project = "cloud-proxy-sql-showcase"
  region = "europe-west3"
}

resource "google_storage_bucket" "funky-bucket" {
  name = "batch-pipeline-sma"
  storage_class = "REGIONAL"
  location  = "europe-west3"
}
