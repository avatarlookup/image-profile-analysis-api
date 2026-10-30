# Image Profile Analysis API | AvatarLookup

Portrait attribute analysis for an uploaded or linked image.

- Product: https://avatarlookup.com/products/image_profile
- API documentation: https://avatarlookup.com/api-docs
- Get an API key: https://avatarlookup.com/register?utm_source=github&utm_medium=referral&utm_campaign=avatarlookup_image-profile-analysis-api
- Base URL: `https://avatarlookup.com`; authentication: `X-API-Key`.
- Public product code: `image_profile`.

## Request

Supply a public HTTPS image URL, a base64 image via `image_b64`, or a multipart `file` upload. JPEG, PNG, WebP and GIF are supported, up to 2 MiB. `service_type` selects `image_profile`; there is no phone/email identifier input. The image is not stored by AvatarLookup.

```bash
curl -X POST 'https://avatarlookup.com/api/v1/image/profile' \
  -H 'X-API-Key: YOUR_API_KEY' \
  -H 'Content-Type: application/json' \
  -d '{"service_type":"image_profile","image_url":"https://example.com/portrait.jpg"}'
```

## Determined response

```json
{
  "code": 0,
  "msg": "ok",
  "data": {
    "service_type": "image_profile",
    "identifier": "img:example",
    "category": "individual portrait",
    "age": 27,
    "gender": "male",
    "skin_color": "unknown",
    "hair_color": "black"
  }
}
```

Result attributes are **directly under `data`**, not under `extra`. Internal `provider` and `profile_available` fields are not exposed. A successful response uses `code=0`; an error is not a negative result.

| Field | Meaning |
|---|---|
| `category` | Image category estimated from the avatar. |
| `age` | Integer estimate; omitted when undetermined. |
| `gender` | Gender presentation estimated from the avatar. |
| `skin_color` | Skin-tone estimate derived from the avatar. |
| `hair_color` | Hair-colour estimate derived from the avatar. |

Image attributes are algorithmic estimates, not verified facts about a person. String attributes may be `unknown` or empty; `age` is an integer and is omitted when undetermined. Do not use estimates for high-impact decisions or unauthorized profiling.



## Errors and limits

The default per-user concurrency budget is five in-flight requests.  `40000`/`40001` are request or input errors, `40100` invalid key, `40200` insufficient balance, `42200` undetermined and uncharged, `42900`/`42901` rate/concurrency limits, and `50300` maintenance. Honor `Retry-After`. Do not automatically repeat a potentially billed request after an uncertain network timeout.

`GET /api/v1/balance` returns `data.balance_micros` (1,000,000 = USD 1). See [current pricing](https://avatarlookup.com/pricing); prices are not hard-coded here.

## Examples and resources

Seven server-side examples: [Python](examples/python), [Node.js](examples/nodejs), [Go](examples/go), [Java](examples/java), [C#](examples/csharp), [PHP](examples/php), [Shell](examples/shell). Follow each language README. Python and Node.js support image JSON requests; other clients demonstrate the request shown in their source.

Read `AVATARLOOKUP_API_KEY` from the server environment. Never commit it or expose it in browser code. Only submit identifiers/images you are authorized to process.

- Product: https://avatarlookup.com/products/image_profile
- Documentation: https://avatarlookup.com/api-docs
- Registration: https://avatarlookup.com/register?utm_source=github&utm_medium=referral&utm_campaign=avatarlookup_image-profile-analysis-api
- OpenAPI: [openapi.yaml](openapi.yaml)
- License: [MIT](LICENSE)

*Last reviewed: 2026-10-30 · Maintained by AvatarLookup.*
