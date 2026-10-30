#!/usr/bin/env bash
set -euo pipefail
: "${AVATARLOOKUP_API_KEY:?Set AVATARLOOKUP_API_KEY}"
curl -fsS -X POST 'https://avatarlookup.com/api/v1/image/profile' -H "X-API-Key: $AVATARLOOKUP_API_KEY" -H 'Content-Type: application/json' -d '{"service_type":"image_profile","image_url":"https://example.com/portrait.jpg"}'
