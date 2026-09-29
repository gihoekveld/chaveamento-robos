# Deploy do backend no Fly.io

O backend usa o `Dockerfile` desta pasta. O `fly.toml` aponta para a região de São Paulo (`gru`) e para a porta 8080. A máquina pode parar quando estiver ociosa; o primeiro acesso após a parada pode demorar enquanto ela inicia.

## Criar e configurar o app

O app `chaveamento-robos` já foi criado. Configure nele os mesmos valores atualmente usados pelo backend na Render:

| Variável | Uso |
| --- | --- |
| `PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, `PGPASSWORD` | Banco PostgreSQL existente |
| `PGSSLMODE` | Modo SSL exigido pelo provedor do banco, geralmente `require` para Supabase |
| `JWT_SECRET` | Mesma chave atual, para manter os tokens existentes válidos |
| `FRONTEND_URL` | Origem HTTPS exata do frontend, sem barra final |
| `SUPABASE_URL`, `SUPABASE_SERVICE_ROLE_KEY`, `SUPABASE_BUCKET` | Storage já usado pela aplicação |

Cadastre valores sensíveis com `flyctl secrets set -a chaveamento-robos NOME=valor`. Não salve senhas ou chaves no repositório. `PORT` já está definido no `fly.toml`.

Depois, execute:

```powershell
flyctl deploy -a chaveamento-robos
flyctl status -a chaveamento-robos
```

O backend ficará em `https://chaveamento-robos.fly.dev`. Configure `VITE_API_URL` com essa URL no serviço de hospedagem do frontend e publique uma nova versão do frontend, pois o Vite incorpora a variável durante o build. Confirme login e operações de torneios/times antes de desligar o backend da Render. Se o PostgreSQL também estiver na Render, migre ou mantenha esse banco separadamente; mover o container não transfere os dados.
