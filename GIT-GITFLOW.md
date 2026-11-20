# Guía de Git, GitHub y Git Flow (práctica)

> Esta guía es para trabajar y estudiar. Respeta las reglas específicas de autoría y de uso de IA de tu evaluación.

## 1. Configuración y autenticación

Instala Git y GitHub CLI (`gh`). En la terminal de IntelliJ / VS Code:

```bash
git --version
gh --version
git config --global user.name "Kevin Salazar"
git config --global user.email "TU_CORREO_DE_GITHUB"
gh auth login
# Selecciona GitHub.com > HTTPS > Login with a web browser
gh auth status
```

**No pongas contraseñas ni tokens en archivos del proyecto.** Si `gh` no está instalado, instala GitHub CLI desde https://cli.github.com/.

## 2. Conectar el proyecto con GitHub

En una carpeta nueva con archivos locales (sustituye `https://github.com/AresSalamar/upc-pre-202620-1asi0729-PC2-7750-KevinSalazar.git` por la dirección del repositorio):

```bash
git init
git branch -M main
git remote add origin https://github.com/AresSalamar/upc-pre-202620-1asi0729-PC2-7750-KevinSalazar.git
git remote -v
git add .
git commit -m "chore: initialize project structure"
git push -u origin main
```

**Si clonaste un repositorio que ya existe, NO hagas `git init`:**

```bash
git clone https://github.com/AresSalamar/upc-pre-202620-1asi0729-PC2-7750-KevinSalazar.git
cd upc-pre-202620-1asi0729-PC2-7750-KevinSalazar
git status
git remote -v
```

**Si el remoto ya tiene commits y tienes archivos locales independientes**, clona primero y copia tus cambios dentro. Evita `git push --force` o mezclar historiales sin inspeccionarlos.

## 3. Git Flow: ramas e incrementos por bounded context

El flujo recomendado es `main` (liberaciones), `develop` (integración) y `feature/<bounded-context>-<capacidad>` (trabajo). Si `develop` todavía no existe:

```bash
git switch main
git pull --ff-only origin main
git switch -c develop
git push -u origin develop
```

Para construir una capacidad del bounded context `logistics`:

```bash
git switch develop
git pull --ff-only origin develop
git switch -c feature/logistics-domain
# Crea el agregado, value objects y reglas de negocio.
git status
git add src/main/java/
git commit -m "feat(logistics): define delivery order domain model"
git push -u origin feature/logistics-domain
```

Abre un Pull Request **`feature/logistics-domain` → `develop`** en GitHub (o con `gh pr create --base develop --head feature/logistics-domain`). Fusiona la PR luego de revisar. Continúa de forma incremental:

```bash
git switch develop
git pull --ff-only origin develop
git switch -c feature/logistics-application
# Implementa commands, services y el contrato Result.
git add .
git commit -m "feat(logistics): implement create delivery order use case"
git push -u origin feature/logistics-application
```

Repite para `feature/logistics-infrastructure`, `feature/logistics-api` y `feature/shared-i18n`, con PRs hacia `develop`. No es obligatorio que sean exactamente estos nombres: preserva la idea de separar avances verificables.

## 4. Release y sincronización

Después de integrar las PRs en `develop`, prepara una rama de release:

```bash
git switch develop
git pull --ff-only origin develop
git switch -c release/1.0.0
git push -u origin release/1.0.0
```

Abre y fusiona una PR **`release/1.0.0` → `main`**. Después sincroniza `develop` con `main` (puedes usar PR `main` → `develop`):

```bash
git switch main
git pull --ff-only origin main
git switch develop
git pull --ff-only origin develop
git merge main
git push origin develop
```

Comprueba la sincronización:

```bash
git fetch origin
git log --oneline --graph --all -12
git diff origin/main origin/develop
git status
```

`git diff` sin salida indica contenido igual, aunque puede variar el historial. No elimines ramas ajenas ni fuerces pushes en repositorios compartidos.

## 5. Conventional Commits frecuentes

```text
feat(logistics): add delivery window value object
feat(logistics): persist delivery orders
fix(logistics): reject duplicate delivery codes
feat(shared): localize validation messages
docs: document API usage
chore: configure database and application properties
```

## 6. Errores típicos

- `remote origin already exists`: comprueba `git remote -v`; modifica con `git remote set-url origin https://github.com/AresSalamar/upc-pre-202620-1asi0729-PC2-7750-KevinSalazar.git` si realmente es otro remoto.
- `Authentication failed`: ejecuta `gh auth login` y verifica acceso al repositorio.
- `non-fast-forward`: `git fetch` y `git pull --rebase` **solo en tu rama de trabajo** cuando sepas qué cambios vienen del remoto.
- Archivos `.env`, claves y contraseñas: añade exclusiones a `.gitignore` antes del primer commit.

## 7. Entrega y autoría

Realiza commits incrementales durante tu práctica. Para la PC2, el profesor exige URL de repositorio privado en `README.md` y que `main` y `develop` estén sincronizadas al terminar. La IA generativa está prohibida durante la evaluación salvo el autocompletado autorizado del IDE; este material es **solo para estudio previo**.
