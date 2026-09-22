# Image Profile Analysis API — Portrait Attributes from an Image | AvatarLookup

The **AvatarLookup Image Profile Analysis API** reads portrait attributes from an image you supply. Unlike the other AvatarLookup products it takes an **image**, not an identifier, so it has its own endpoint: `POST /api/v1/image/profile`. This is the official AvatarLookup example repository, with integrations in seven languages.

- **Product page:** https://avatarlookup.com/products/image_profile
- **API documentation:** https://avatarlookup.com/api-docs
- **API base URL:** `https://avatarlookup.com`
- **Authentication:** `X-API-Key`
- **Get an API key:** https://avatarlookup.com/register

## How do I submit an image?

Two shapes are accepted. API integrations normally use JSON with a URL:

```bash
curl -X POST 'https://avatarlookup.com/api/v1/image/profile' \
  -H 'X-API-Key: YOUR_API_KEY' \
  -H 'Content-Type: application/json' \
  -d '{"product":"image_profile","image_url":"https://example.com/portrait.jpg"}'
```

`image_b64` accepts a base64 payload instead of a URL. A `multipart/form-data` request with a `file` field and a `product` field works as well.

```json
{
  "code": 0,
  "msg": "ok",
  "data": {
    "service_type": "image_profile",
    "extra": {
      "category": "individual portrait",
      "gender": "male",
      "age": "27",
      "skin_color": "white",
      "hair_color": "black"
    }
  }
}
```

## What are the response fields?

| Field | Description |
|---|---|
| `extra.category` | Image category, for example `individual portrait`. |
| `extra.gender` | Presented gender estimated from the image. |
| `extra.age` | Estimated age. |
| `extra.skin_color` | Estimated skin tone. |
| `extra.hair_color` | Estimated hair colour. |

These are **algorithmic estimates derived from an image**. They are auxiliary references only — not verified demographic facts about a real person — and must not drive high-impact decisions, screening, tracking, or profiling the subject has not agreed to.

## What are the limits and error codes?

- The image is passed straight to the analysis upstream and is **not stored**: it is never written to disk or object storage by AvatarLookup.
- `40000`/`40001` are request or JSON errors; `40100` is an invalid key; `40200` is insufficient balance; `42200` is undetermined and uncharged; `42900` is rate limited — honour `Retry-After`; `50300` is temporary maintenance.
- `GET /api/v1/balance` returns the current balance in `data.balance_micros`.

Keep the key on a trusted server and read it from `AVATARLOOKUP_API_KEY`. Never commit it or expose it in production browser code.

## Runnable examples in seven languages

| Language | Example |
|---|---|
| Python | [`examples/python`](examples/python) |
| Node.js | [`examples/nodejs`](examples/nodejs) |
| Go | [`examples/go`](examples/go) |
| Java | [`examples/java`](examples/java) |
| C# | [`examples/csharp`](examples/csharp) |
| PHP | [`examples/php`](examples/php) |
| Shell / curl | [`examples/shell`](examples/shell) |

## Official resources and responsible use

- **Product:** https://avatarlookup.com/products/image_profile
- **Documentation:** https://avatarlookup.com/api-docs
- **Registration:** https://avatarlookup.com/register
- **Pricing:** https://avatarlookup.com/pricing
- **OpenAPI contract:** [`openapi.yaml`](openapi.yaml)
- **License:** [MIT](LICENSE) for the sample code

Submit only images you are authorized to process, and comply with applicable privacy laws. This product is not facial recognition, identity verification, or proof of anything about the person shown.

---

*Last reviewed: 2026-09-22 · Maintained by AvatarLookup. Canonical product page: https://avatarlookup.com/products/image_profile*
