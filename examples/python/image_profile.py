import os

import requests

api_key = os.environ["AVATARLOOKUP_API_KEY"]
base_url = os.getenv("API_BASE_URL", "https://avatarlookup.com")
image_url = os.getenv("IMAGE_URL", "https://example.com/portrait.jpg")

response = requests.post(
    f"{base_url}/api/v1/image/profile",
    headers={"X-API-Key": api_key},
    json={"service_type": "image_profile", "image_url": image_url},
    timeout=60,
)
response.raise_for_status()
result = response.json()
if result.get("code") != 0:
    raise RuntimeError(f"API error: {result}")
print(result)
